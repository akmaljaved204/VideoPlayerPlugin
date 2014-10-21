var videoplayer = {
    createEvent: function(videoURL,successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'VideoPlayer', // mapped to our native Java class called "CalendarPlugin"
            'triggerVideoPlayer', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "videoCURL":videoURL
                
            }]
        ); 
    }
};
module.exports = videoplayer;