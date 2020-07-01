// 定义Client
var knex = require('knex')({
  client: 'mysql',
  connection: {
    host : 'localhost',
    user : 'localhost',
    password : 'localhost',
    database : 'cAuth'
  }
});

async function getMessages() {
  // 查询
  return await knex('cSecreteMessage').select().then(function(rows) {
    // console.log(rows);
    rows.forEach(function (value, index, self) {
        // console.log(value);
        // console.log(index);
        // console.log(self);
    });
    return rows;
  });
}

getMessages().then((res) => {
  console.log(res);
});

var record = {
  uuid: '8829b926-4ae1-2538-e66a-ea4f092f307d', //Math.random.toString().substr(0, 40),
  pic: '',
  msg: ''
}
knex('cSecreteMessage').insert(record).then((res) => {
  console.log(res);
}).catch((e) => {
  console.log(e);
});
