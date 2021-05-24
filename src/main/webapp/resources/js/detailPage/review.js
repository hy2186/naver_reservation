$(document).ready(function() {
    const reviewPageLoader = new ReviewPageLoader();
    reviewPageLoader.requestComments();
});

function ReviewPageLoader() {
}
ReviewPageLoader.prototype = {
    "requestComments": function() {
        $.ajax({
            type: "GET",
            url: `/reservation/api/products/${getParameter("productId")}/comments`,
            caches: false
        })
        .done(function(data) {
            this.initPageView(data);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "initPageView": function(commentData) {
        const title = getParameter("title");
        $("#product_title").text(title);
        commentData.title = title;

        const reviewView = new ReviewView();
        reviewView.initReviewPage(commentData);
    }
}
