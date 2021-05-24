function ReviewView() {
}
ReviewView.prototype = {
    "initDetailPage": function(displayData) {
        this.display = displayData;
        this.makeReviewHeader(displayData);
        this.makeReviewContent(displayData);
        this.activateMoreButton();
    },

    "initReviewPage": function(displayData) {
        this.display = displayData;
        this.makeReviewHeader(displayData);
        this.makeReviewContent(displayData);
    },

    "makeReviewHeader": function(displayData) {
        let averageScore = displayData.averageScore.toFixed(1);

        $("#review_graph").css("width", 100 * averageScore / 5.0 + "%");
        $("#review_score").text(averageScore);
        $("#review_count").text(displayData.commentCount + "건");
    },

    "makeReviewContent": function(displayData) {
        let reviewTemplate = $("#review_template").text();
        let bindTemplate = Handlebars.compile(reviewTemplate);

        Handlebars.registerHelper("fileId", function(commentImages) {
            return commentImages[0].fileId;
        });
        Handlebars.registerHelper("score", function(comment) {
            return comment.score.toFixed(1);
        });
        Handlebars.registerHelper("reservationDate", function(comment) {
            return getDateString(new Date(comment.reservationDate));
        });

        $("#short_review_list").append(bindTemplate(displayData));
    },

    "activateMoreButton": function() {
        if (this.display.commentCount > 3) {
            $("#review_more_button").attr("href", `resources/html/review.html?productId=${this.display.displayInfo.productId}&title=${this.display.displayInfo.productDescription}`);
        } else {
            $("#review_more_button").hide();
        }
    },
};
