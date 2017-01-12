/**
 * Created by pengshu on 2016/11/16.
 */
var count;
var outstr = "";
var setpage = function (totalpage, pagesize, cpage) {

    //总页数小于十页
    if (totalpage <= 10) {
        for (count = 1; count <= totalpage; count++) {
            if (count != cpage) {
                outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage(" + count + ")'>" + count + "</a>";
            } else {
                outstr = outstr + "<span class='current' >" + count + "</span>";
            }
        }
    }
    //总页数大于十页
    if (totalpage > 10) {
        if (parseInt((cpage - 1) / 10) == 0) {
            for (count = 1; count <= 10; count++) {
                if (count != cpage) {
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage(" + count + ")'>" + count + "</a>";
                } else {
                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
            outstr = outstr + "<a style='width: 50px;' href='javascript:void(0)' onclick='gotopage(" + count + ")'> 下十页 </a>";
        }
        else if (parseInt((cpage - 1) / 10) == parseInt(totalpage / 10)) {
            outstr = outstr + "<a style='width: 50px;' href='javascript:void(0)' onclick='gotopage(" + (parseInt((cpage - 1) / 10) * 10) + ")'>上十页</a>";
            for (count = parseInt(totalpage / 10) * 10 + 1; count <= totalpage; count++) {
                if (count != cpage) {
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage(" + count + ")'>" + count + "</a>";
                } else {
                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
        }
        else {
            outstr = outstr + "<a style='width: 50px;' href='javascript:void(0)' onclick='gotopage(" + (parseInt((cpage - 1) / 10) * 10) + ")'>上十页</a>";
            for (count = parseInt((cpage - 1) / 10) * 10 + 1; count <= parseInt((cpage - 1) / 10) * 10 + 10; count++) {
                if (count != cpage) {
                    outstr = outstr + "<a href='javascript:void(0)' onclick='gotopage(" + count + ")'>" + count + "</a>";
                } else {
                    outstr = outstr + "<span class='current'>" + count + "</span>";
                }
            }
            outstr = outstr + "<a style='width: 50px;' href='javascript:void(0)' onclick='gotopage(" + count + ")'> 下十页 </a>";
        }
    }
    document.getElementById("setpage").innerHTML = "<div id='setpage'><span id='info'>共" + totalpage + "页|第" + cpage + "页<\/span>" + outstr + "<\/div>";
    outstr = "";
}

