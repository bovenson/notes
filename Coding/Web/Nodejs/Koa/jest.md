---
title: koa & jest 单元测试
tags:
	- Koa
	- Jest
categories:
	- Nodejs
---

# 配置

**`app.js`**

```javascript
const path = require('path');
const Koa = require('koa');
const app = new Koa();
const middleware = require('./middleware/index');

global.APP_PATH = __dirname;
global.join = (...args) => {
  args.unshift(APP_PATH);
  return path.join.apply(path, args);
};

const config = require(join('config'));

middleware(app, config);

app.use(async (ctx, next) => {
  ctx.status = 404;
  ctx.body = '404';
});

module.exports = app.listen(config.SERVER_PORT, err => {
  if (err) {
    console.error(err);
  } else {
    console.log(`http://localhost:${config.SERVER_PORT}`);
  }
});
```

`goods.test.js`

```javascript
const server = require('../app');
const request = require('supertest');

afterEach(() => {
    server.close();
});

test('分类主页', async () => {
    const response = await request(server).get('/goods/list');
    expect(response.status).toBe(200);
});
```

`package.json`

```json
{
  "name": "koa-api",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "jest",
    "start": "nodemon app.js"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "dependencies": {
    "koa": "^2.5.0",
    "koa-bodyparser": "^4.2.0",
    "koa-log4": "^2.3.0",
    "koa-multer": "^1.0.2",
    "koa-router": "^7.4.0",
    "koa-session2": "^2.2.5",
    "koa-xml-body": "^2.0.0",
    "moment": "^2.22.2",
    "think-model": "^1.2.2",
    "think-model-mysql": "^1.0.6",
    "unirest": "^0.5.1"
  },
  "devDependencies": {
    "jest": "^22.4.4",
    "nodemon": "^1.18.3",
    "supertest": "^3.1.0"
  },
  "jest": {
    "moduleFileExtensions": ["js", "jsx", "json", "ts", "tsx"],
    "collectCoverageFrom": [
      "**/*.{ts,js}",
      "!**/node_modules/**",
      "!**/build/**",
      "!**/coverage/**"
    ],
    "testRegex": "(/__tests__/.*|(\\.|/)(test|spec))\\.(js|ts)x?$",
    "testPathIgnorePatterns": [
      "/node_modules/",
      "/build/",
      "/coverage/"
    ]
  }
}
```

**运行**

```shell
$ npm test

> koa-api@1.0.0 test /Users/bovenson/Git/zhijia-api/koa-api
> jest

  console.warn node_modules/unirest/node_modules/node-uuid/uuid.js:48
    [SECURITY] node-uuid: crypto not usable, falling back to insecure Math.random()

  console.log app.js:25
    http://localhost:3002

 PASS  test/goods.test.js
  ✓ 分类主页 (90ms)

Test Suites: 1 passed, 1 total
Tests:       1 passed, 1 total
Snapshots:   0 total
Time:        1.961s
Ran all test suites.
```

# Jest

## matchers

# 参考

- [参考一](https://medium.com/scrum-ai/4-testing-koa-server-with-jest-week-5-8e980cd30527)
- [参考二](https://blog.csdn.net/itheima_Wujie/article/details/78566617)
- [Github](https://github.com/robinpokorny/jest-example-koa/tree/master/api/test)