---
title: 参数验证
tags:
	- Koa
categories:
	- Nodejs
---

```javascript
/**
 * 请求参数验证
 * @params  参数列表及其规则; 
 *          规则: {required: 必需的参数, 
 *                string: String类型参数, 
 *                number: number类型参数,
 *                int:    integer类型参数
 *                object: object类型
 *                array:  array类型参数}
 * @example logicValidate({page: 'required', type: ['required', 'string']})
 */
module.exports = (rulesDict) => {
    /**
     * 根据请求方式及参数名获取参数值
     * @param {koa.context} ctx koa.context
     * @param {*} name          参数名
     */
    function getRequestParam (ctx, name) {
        switch (ctx.method.toLowerCase()) {
            case 'get':
                return ctx.query[name];
            case 'post':
                return ctx.request.body[name];
        }
    }
    return async (ctx, next) => {
        let flag = false;
        let data = {};
        for (let param in rulesDict) {
            let rules = rulesDict[param];
            if (Array.isArray(rules)) {
                for (let index in rules) {
                    let rule = rules[index];
                    checkRule(rule.toLowerCase());
                }
            } else {
                checkRule(rules.toLowerCase())                
            }

            /**
             * 检查是否符合要求
             * @param {string} rule rule, lower case
             * pass: 需要局部变量，不要提取到函数外
             */
            function checkRule(rule) {
                let paramVal = getRequestParam(ctx, param);
                if (paramVal === null || typeof(paramVal) === 'undefined') {    // 如果参数值为空
                    if (rules && (rules === 'required' || rules.indexOf('required') >= 0)) {    // 如果有required规则
                        data[param] = param + ' can not be blank';
                        flag = true;
                    }
                } else if (rule === 'int') {
                    if (!Number.isInteger(paramVal)) {
                        data[param] = param + ' must be int';
                        flag = true;
                    }
                } else if (rule !== 'required' && typeof(paramVal) !== rule) {
                    data[param] = param + ' must be ' + rule;
                    flag = true;
                }
            }
        }

        if (flag) {
            ctx.body = {
                errno: 1000,
                errmsg: 'validate error',
                data
            }
        } else {
            await next();
        }
    }
}
```

