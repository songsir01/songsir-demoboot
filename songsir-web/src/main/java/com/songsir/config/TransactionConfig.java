package com.songsir.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;

/**
 * @PackageName com.songsir.config
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 15:46 2019/7/9
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
@Aspect
@Configuration
public class TransactionConfig {

    private static final String AOP_POINTCUT = "execution (* com.songsir.service.impl.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        //可以实现对目标对象的每个方法实施不同的事务管理
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        /* 只读事务,不做更新操作 */
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();

        //设置只读属性
        readOnlyTx.setReadOnly(true);
        //设置事务控制
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        /*当前存在事务就用当前事务,当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();

        //设置回滚规则
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        //
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // requiredTx.setTimeout(TX_METHOD_TIMEOUT);

        HashMap<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("get*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("save*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("cancel*", requiredTx);
        txMap.put("cancle*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("merge*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("overtime*", requiredTx);
        txMap.put("manualCancle*", requiredTx);
        txMap.put("des*", requiredTx);
        txMap.put("reg*", requiredTx);
        txMap.put("confirm*", requiredTx);
        txMap.put("pay*", requiredTx);
        txMap.put("repair*", requiredTx);

        source.setNameMap(txMap);

        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
