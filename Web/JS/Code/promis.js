async function judgeScore(val, resolve) {
    // 处理val
    await setTimeout(function() {
            console.log("我考试完了, 用时: ", val, " 秒");
            resolve(val);
        }, val * 1000);
}

if (!Promise.map) {
    Promise.map = function(vals, cb) {
        return Promise.all(
            vals.map(function(val) {
                return new Promise(function(resolve) {
                    cb(val, resolve);
                });
            })
        );
    }
}

async function judge (arr) {
    arr = [6, 2, 3, 1, 4, 5, 4];
    console.log("所有考生都已交卷")
    console.log("他们的答案是: ", arr, " 给每个人评分的时间、成绩都和答案相同")
    var res = await Promise.map(arr, judgeScore);
    console.log("开始统计成绩...")
    console.log("所有考生的成绩: ", res)
}

judge()
