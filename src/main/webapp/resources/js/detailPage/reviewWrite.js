$(document).ready(function() {
    const reviewWritePageLoader = new ReviewWritePageLoader();
    reviewWritePageLoader.initPageView();
});

function ReviewWritePageLoader() {
}
ReviewWritePageLoader.prototype = {
    "initPageView": function() {
        const ratingScoreView = new RatingScoreView();
        const textInputView = new TextInputView();
        const imageUploadView = new ImageUploadView();
        const submissionForm = new SubmissionForm();

        $(".title").text(getParameter("title"));
        ratingScoreView.init();
        textInputView.init();
        imageUploadView.init();
        submissionForm.activateSubmitButton();
    },
}

function RatingScoreView() {
}
RatingScoreView.prototype = {
    "init": function() {
        this.$stars = $(".rating_rdo");
        this.$scoreBox = $(".star_rank");

        this.activateRatingScore();
    },

    "activateRatingScore": function() {
        const self = this;
        $(".rating").on("click", ".rating_rdo", function() {
            const score = $(this).val();

            self.updateStars(score);
            self.changeScore(score);
        });
    },

    "updateStars": function(score) {
        this.$stars.each(function(index, star) {
            $(star).removeClass("checked");
            $(star).prop("checked", false);
        });

        for (let index = 0; index < score; index++) {
            $(this.$stars[index]).addClass("checked");
        }
    },
    
    "changeScore": function(score) {
        this.$scoreBox.val(score);
        this.$scoreBox.text(score);
        this.$scoreBox.removeClass("gray_star");   
    }
}

function TextInputView() {
}
TextInputView.prototype = {
    "init": function() {
        this.$inputTextBox = $(".review_write_info");
        this.$textArea = $(".review_textarea");
        this.$textLengthBox = $("#text_length");

        this.activateTextInputBox();
        this.activateTextAreaKeyUpEvent();
        this.activateFocusOutEvent();
    },

    "activateTextInputBox": function() {
        const self = this;
        self.$inputTextBox.on("click", function() {
            self.$inputTextBox.hide();
            self.$textArea.focus();
        });
    },

    "activateTextAreaKeyUpEvent": function() {
        const self = this;
        self.$textArea.on("keyup", function() {
            self.changeTextLength();
            self.preventTextInput();
        });
    },

    "activateFocusOutEvent": function() {
        const self = this;
        self.$textArea.on("focusout", function() {
            const text = self.$textArea.val();
            if (!text) {
                self.$inputTextBox.show();
            }
        });
    },

    "changeTextLength": function() {
        this.$textLengthBox.text(this.$textArea.val().length);        
    },

    "preventTextInput": function() {
        const MAX_TEXT_LENGTH = 400;
        if (this.$textArea.val().length > MAX_TEXT_LENGTH) {
            const text = this.$textArea.val().substring(0, MAX_TEXT_LENGTH);
            this.$textArea.val(text);
            this.$textLengthBox.text(this.$textArea.val().length); 
        }
    },
}

function ImageUploadView() {
}
ImageUploadView.prototype = {
    "init": function() {
        this.$thumbNail = $(".item_thumb");
        this.$thumbNailList = $("#thumb_nail_list");

        this.activateImageUpload();
        this.activateImageRemove();
    },

    "activateImageUpload": function() {
        const self = this;
        $("#reviewImageFileOpenInput").on("change", function() {
            const image = this.files[0];
            if (InputFormValidation.prototype.isValidImageType(image)) {
                self.$thumbNail.attr("src", window.URL.createObjectURL(image));
                self.$thumbNailList.show();
            } else {
                alert("이미지 파일 형식이 올바르지 않습니다.");
            }
        });
    },

    "activateImageRemove": function() {
        const self = this;
        $("#remove_image_button").on("click", function() {
            self.$thumbNail.attr("src", "");
            self.$thumbNailList.hide();
        });
    }
}

function InputFormValidation() {
}
InputFormValidation.prototype = {
    "isValidScore": function(score) {
        return (score >= 1 && score <= 5);
    },

    "isValidComment": function(comment) {
        return (comment.length >= 5 || comment.length <= 400);
    },

    "isValidImageType": function(image) {
        return (/(.jpg|.png)$/).test(image.name);
    }
}

function SubmissionForm() {
}
SubmissionForm.prototype = {
    "activateSubmitButton": function() {
        const inputFormValidation = new InputFormValidation();

        const self = this;
        $("#submit_button").on("click", function() {
            const score = $(".star_rank").val();
            if (!inputFormValidation.isValidScore(score)) {
                alert("별점을 선택해주세요.");
                return;
            }

            const comment = $(".review_textarea").val();
            if(!inputFormValidation.isValidComment(comment)) {
                alert("5글자 이상 리뷰를 입력하세요.");
                return;
            }

            self.submitForm();
        });
    },

    "submitForm": function() {
        const image = $("#reviewImageFileOpenInput")[0].files[0];

        if (!image) {
            $.ajax({
                type: "POST",
                url: this.makeRequestUrl()
            })
            .done(function() {
                location.href = "/reservation/myreservation";
                alert("리뷰등록 완료!");
            })
            .fail(function(response) {
                alert("에러발생 : " + response.status + response.responseText);
            });
        } else {
            $.ajax({
                type: "POST",
                url: this.makeRequestUrl(),
                enctype: "multipart/form-data",
                data: this.makeFormData(image),
                contentType: false,
                processData: false
            })
            .done(function() {
                location.href = "/reservation/myreservation";
                alert("리뷰등록 완료!");
            })
            .fail(function(response) {
                alert("에러발생 : " + response.status + response.responseText);
            });
        }
    },

    "makeRequestUrl": function() {
        const score = $(".star_rank").val();
        const comment = $(".review_textarea").val();

        return `/reservation/api/reservations/${getParameter("reservationInfoId")}/comments?comment=${comment}&productId=${getParameter("productId")}&score=${score}`;
    },

    "makeFormData": function(image) {
        const formData = new FormData();
        formData.append("attachedImage", image);

        return formData;
    }
}