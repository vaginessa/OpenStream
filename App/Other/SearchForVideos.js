var sources = document.getElementsByTagName("source");
var final = "<p>Please click on video you want to play:</p><br>";

for (var i = 0;i<sources.length;i++) {
    final += "<a href='#' onclick='play(this.innerHTML);'>" + sources[i].src + "</a><br>";
}
document.body.innerHTML = final;

function play(link) {
    window.JSInterface.playVideo(link);
}