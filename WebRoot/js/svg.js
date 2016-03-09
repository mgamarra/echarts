window.addEventListener('load', function() {
    "use strict";
    var svg = document.getElementById("svg");
    var svgsource = document.getElementById("svgsource");
    var paths = svgsource.getElementsByTagName('path');
    var wo = 125;
    var ho = 180;
    var wt = 400;
    var ht = 400;
    var obj = [];
    for (var k = 0; k < paths.length; k++) {
        var p = paths[k];
        var seg = p.pathSegList.getItem(0);
        var x = seg.x;
        var y = seg.y;
        var xo = x - wo;
        var yo = y - ho;
        var d = Math.sqrt(xo * xo + yo * yo);
        obj.push({
            p: p,
            x: x,
            y: y,
            m: 10,
            s: seg,
            d: d,
            v: false
        });
    }
    obj.sort(function(p0, p1) {
        return p0.d - p1.d;
    });
    var inj = 0;

    function run() {
        requestAnimationFrame(run);
        for (var i = 0, l = obj.length; i < l; i++) {
            var o = obj[i];
            if (o.v) {
                if (o.m > 1) o.m -= 0.05;
                else o.v = false;
                var x = wt + (o.x - wo) * o.m;
                var y = ht + (o.y - ho) * o.m;
                o.s.x = x;
                o.s.y = y;
            }
        }
    }

    function inject() {
        var o = obj[inj++];
        var p = o.p;
        o.v = true;
        var x = wt + (o.x - wo) * o.m;
        var y = ht + (o.y - ho) * o.m;
        o.s.x = x;
        o.s.y = y;
        svg.appendChild(p);
        if (paths.length) setTimeout(inject, 500);
    }
    run();
    inject();
});