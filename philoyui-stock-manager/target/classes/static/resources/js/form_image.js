
$(function() {
    var imageFormIdArray = imageFormIds.split(",");
    for(var i= 0;i<imageFormIdArray.length;i++){
        prepare_image_form(imageFormIdArray[i]);
    }
});

function prepare_image_form(fieldName){

  var btn = document.getElementById('upload-btn_' + fieldName),
  wrap = document.getElementById('pic-progress-wrap_' + fieldName),
  picBox = document.getElementById('picBox_' + fieldName),
  errBox = document.getElementById('errormsg_' + fieldName),
  inputElement = document.getElementById(fieldName);

  var uploader = new ss.SimpleUpload({
        button: btn,
        url: '/formImageUpload',
        name: 'multipartFile',
        multiple: true,
        multipart: true,
        maxUploads: 2,
        maxSize: 1024,
        queue: false,
        allowedExtensions: ['jpg', 'jpeg', 'png', 'gif'],
        accept: 'image/*',
        debug: true,
        hoverClass: 'btn-hover',
        focusClass: 'active',
        disabledClass: 'disabled',
        responseType: 'json',
        onSubmit: function(filename, ext) {
           var prog = document.createElement('div'),
               outer = document.createElement('div'),
               bar = document.createElement('div'),
               size = document.createElement('div'),
               self = this;

            prog.className = 'prog';
            size.className = 'size';
            outer.className = 'progress progress-striped';
            bar.className = 'progress-bar progress-bar-success';

            outer.appendChild(bar);
            prog.appendChild(size);
            prog.appendChild(outer);
            wrap.appendChild(prog); // 'wrap' is an element on the page

            self.setProgressBar(bar);
            self.setProgressContainer(prog);
            self.setFileSizeBox(size);

            errBox.innerHTML = '';
            btn.value = 'Choose another file';
          },
          onSizeError: function() {
                errBox.innerHTML = 'Files may not exceed 1024K.';
          },
          onExtError: function() {
              errBox.innerHTML = 'Invalid file type. Please select a PNG, JPG, GIF image.';
          },
    	  onComplete: function(file, response, btn) {
            if (!response) {
              errBox.innerHTML = 'Unable to upload file';
            }
            if (response.success === true) {
              picBox.innerHTML = '<a target="_blank" href="' + response.file + '"><img width="200" src="' + response.file + '"></a>';
              inputElement.value = response.file;
            } else {
              if (response.msg)  {
                errBox.innerHTML = response.msg;
              } else {
                errBox.innerHTML = 'Unable to upload file';
              }
            }

          }
	});

};
