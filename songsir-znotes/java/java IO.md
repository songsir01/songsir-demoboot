
# 一、需求背景

公司为了对接某数据分析产品A（缩写是YGFZ），需要将数据库订单数据按照A公司格式要求导出到txt文本，然后才能进一步导入到A产品中进行数据分析。

其中要求的格式为：每行有10个订单数据，每个数据为Json字符串格式，也就是每行是一个JsonArray，每个JsonArray有10个元素。

# 二、分析

目前订单是千万级数据库，每个表10G左右，不可能一次全部查出来，然后在分割成10个10个一组。

那么，这里首先根据下单时间查询，按年份输出。比如2018年数据，先查出总数据条数，这个速度很快，三百万数量3秒以内。这三百万数据也是很庞大，然后分页查询，比如每次查询10万条，然后把这10万条组成的ArrayList分割成10个10个小的ArrayList。

```
// 分割的数组包含fromIndex，不包含toIndex
List<E> subList(int fromIndex, int toIndex);
```
接下来，先把这些分割好的list放到一个Map中存储备用。

然后，就开始执行文本输出操作相关了，新建输出流

```
    // 根据时间年月日时分秒定义文件名称
    String strDate = DateUtils.formatDate2Str(new Date(), DateUtils.DATE_TIME_PATTON_3);
    String fileName = "D:\\" + strDate + ".txt";
    File f = new File(fileName);
    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
    // 如果文件不存在，新建文件
    if (!f.exists()) {
        f.createNewFile();
    }
```
然后，遍历刚才存储数据的Map，将数据转为ArrayList（fastJson）输出到文件。

```
// 遍历map，把每10个订单数据一行行输出到文件
Set<Map.Entry<String, Object>> entries = map.entrySet();
for (Map.Entry<String, Object> entry : entries) {
    Object value = entry.getValue();
    // 转为fast JsonArray
    JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(value));
    logger.info(jsonArray.toString());
    bw.write(jsonArray.toString());
    // 换行
    bw.write("\r\n");
    bw.flush();
}
```
# 三、测试

在对测试库3万多数据进行测试时，生成的文件如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181123114728672.png)
不同分页条数时用时如下：

分页条数 | 用时（ms）
---|---
1000 | 15061
5000 | 10228
10000 | 9018
50000 | 8824

由于测试库一共3万多条数据，所以分页5万10万没什么区别。在数据量大的时候分页数据越大，则数据连接关闭的次数越少，此间耗时也降低。千万级数据库，对内存使用很大，所以在分页条数也应有所取舍。

# 四、代码


```
public String getOrderDataWriteToTxt(String startTime, String endTime, Integer pageSize) {
    String uuid = UUID.randomUUID().toString();
    String result = "";
    logger.info("getOrderDataWriteToTxt 接收请求：" + uuid + "参数: startTime = " + startTime + ",endTime = " + endTime);
    try {
        if (StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
            result = ResultUtil.createFailureResult("-10000", "参数有误！");
            return result;
        }
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("saleTimeStart", DataUtil.sdcFormatDateStrToPad(startTime, true));
        paramMap.put("saleTimeEnd", DataUtil.sdcFormatDateStrToPad(endTime, false));
        // 每次从数据库最多查询多少条数据
        if (pageSize == null) {
            pageSize = 1000 * 10;
        }
        if (pageSize % 10 != 0 || pageSize < 10) {
            result = ResultUtil.createFailureResult("-20000", "分页参数不合理！");
            return result;
        }
        // 查询在规定时间内数据总条数
        int count = orderMapper.selectOrderCountToYiGuan(paramMap);
        // 一共能查多少页
        int pageCount = (count % pageSize == 0) ? count / pageSize : (count / pageSize + 1);
        // 根据时间年月日时分秒定义文件名称
        String strDate = DateUtils.formatDate2Str(new Date(), DateUtils.DATE_TIME_PATTON_3);
        String fileName = "D:\\" + strDate + ".txt";
        File f = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        // 如果文件不存在，新建文件
        if (!f.exists()) {
            f.createNewFile();
        }
        HashMap<String, Object> map = new HashMap<>();
        // 分批次查询数据库
        for (int j = 0; j < pageCount; j++) {
            paramMap.put("start", j * pageSize);
            paramMap.put("limit", pageSize);
            // 查询订单数据
            List<OrderNew> orderList = orderMapper.selectOrderToYiGuan(paramMap);
            // 输出每行10个
            int line = 10;
            int size = orderList.size();
            // 一共有多少行
            int a = (size % line == 0) ? size / line : (size / line + 1);
            for (int i = 0; i < a; i++) {
                // 将获取到的订单列表每十个一组放入map中备用
                // subList(int fromIndex, int toIndex) 包含fromIndex，不包含toIndex
                map.put("list" + i, orderList.subList(i * line, line * (i + 1) > size ? size : line * (i + 1)));
            }
            // 遍历map，把每10个订单数据一行行输出到文件
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Object value = entry.getValue();
                // 转为fast JsonArray
                JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(value));
                logger.info(jsonArray.toString());
                bw.write(jsonArray.toString());
                // 换行
                bw.write("\r\n");
                bw.flush();
            }
            map.clear();
        }
        bw.close();
        result = ResultUtil.createSuccessResult();
    } catch (IOException e) {
        e.printStackTrace();
        result = ResultUtil.createFailureResult(e);
    }
    return result;
}
```
以上代码所使用的工具类（略）。