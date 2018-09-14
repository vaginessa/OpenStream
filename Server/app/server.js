/*
   ___                 ___ _                      
  / _ \ _ __  ___ _ _ / __| |_ _ _ ___ __ _ _ __  
 | (_) | '_ \/ -_) ' \\__ \  _| '_/ -_) _` | '  \ 
  \___/| .__/\___|_||_|___/\__|_| \___\__,_|_|_|_|
       |_|                                        
  
  Welcome to the glory code of OpenStream! If you have 
  any ideas to make this code better please make pull 
  request!
  *Insert joke about bad code*

  To be serious this is shitbase level 4256, but I am 
  lazy person.
*/

// -------------------------------------
// Variables
// -------------------------------------

var phoneip = "";
var info = document.getElementById("infotext");
var videocontainer = document.getElementById("video");
var videourl = "";

// -------------------------------------
// Functions
// -------------------------------------
function log(text) {
    var d = new Date();
    var time = "[" + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + "] ";
    console.log(time + text);
    info.innerHTML = time + text;
}

function connect() {
    phoneip = window.prompt("Enter IP adress of your phone or computer","192.168.1.1");
    var button = document.getElementById("connectbutton");
    button.innerHTML = "Change IP (" + phoneip + ")";
    button.className = "button-red";
    setInterval(function(){
        check();
    }, 3000);
}

function check() {
    var request = $.get("http://" + phoneip + "/", function(data) {
        log("Response from " + phoneip + " " + data);
        if (videourl !== data) {
            videourl = data;
            play();
        }
      })
        .fail(function() {
            log("No response on IP " + phoneip);
        });
}

function play() {
    video.innerHTML = "<video id='player' playsinline controls><source src='" + videourl + "' type='video/mp4'></video>";
    var player = new Plyr('#player');
    player.play();
}