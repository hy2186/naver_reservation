$(document).ready(function() {
    const detailPageLoader = new DetailPageLoader();
    detailPageLoader.initPage();
});

function DetailPageLoader() {
}
DetailPageLoader.prototype = {
    "initPage": function() {
        this.requestDisplayData();
    },

    "requestDisplayData": function() {
        $.ajax({
            type: "GET",
            url: "/reservation/api/products/" + getParameter("displayInfoId"),
            caches: false
        })
        .done(function(data) {
            this.initPageView(data);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "initPageView": function(displayData) {
        const sliderView = new SliderView();
        const productContentView = new ProductContentView();
        const reviewView = new ReviewView();
        const detailTabView = new DetailTabView();

        sliderView.init(displayData);
        productContentView.init(displayData.displayInfo, displayData.productPrices);
        reviewView.initDetailPage(displayData);
        detailTabView.init(displayData.displayInfo, displayData.displayInfoImage);
    },
}

function SliderView() {
}
SliderView.prototype = {
    "init": function(displayData) {
        this.$slider = $("#detail_slider");
        this.sliderWidth = this.$slider.css("width").replace("px", "");
        this.$currentPage = $("#current_page");
        this.$totalPage = $("#total_page");

        this.makeSliderImage(displayData);
        this.activateSliderMoveButton();
    },

    "makeSliderImage": function(displayData) {
        const sliderItemTemplate = $("#slider_item_template").text();
        const handlebarsTemplate = Handlebars.compile(sliderItemTemplate);
        this.$slider.append(handlebarsTemplate(displayData));

        this.$totalPage.text(displayData.productImages.length);

        if (displayData.productImages.length <= 1) {
            $("#prev_page_button").hide();
            $("#next_page_button").hide();
        } else {
            const firstImage = $("#detail_slider .item").first().clone(true);
            const secondImage = $("#detail_slider .item").last().clone(true);
            this.$slider.append(firstImage);
            this.$slider.prepend(secondImage);

            this.$slider.css("transform", "translate(-" + this.sliderWidth + "px, 0)");
        }
    },

    "activateSliderMoveButton": function() {
        $("#prev_page_button").on("click", function() {
            const currentIndex = Number(this.$currentPage.text());
            this.moveSlider(currentIndex - 1);
        }.bind(this));
        $("#next_page_button").on("click", function() {
            const currentIndex = Number(this.$currentPage.text());
            this.moveSlider(currentIndex + 1);
        }.bind(this));
    },

    "moveSlider": function(sliderIndex) {
        this.$slider.css("transition", "200ms");
        this.$slider.css("transform", "translate(-" + (sliderIndex * this.sliderWidth) + "px, 0)");

        if (sliderIndex === 0) {
            sliderIndex = Number(this.totalPage.text());
            setTimeout(function() {
                this.$slider.css("transition", "0ms");
                this.$slider.css("transform", "translate(-" + (sliderIndex * this.sliderWidth) + "px, 0)");
            }.bind(this), 200);
        } else if (sliderIndex === Number(this.totalPage.text()) + 1) {
            sliderIndex = 1;
            setTimeout(function() {
                this.$slider.css("transition", "0ms");
                this.$slider.css("transform", "translate(-" + (sliderIndex * this.sliderWidth) + "px, 0)");
            }.bind(this), 200);
        }

        this.$currentPage.text(sliderIndex);
    },
};

function ProductContentView() {
}
ProductContentView.prototype = {
    "init": function(displayInfo, productPrices) {
        this.makeProductDescription(displayInfo.productContent);
        this.makeEventDescription(productPrices);
        this.setReserveButton();
        this.activateFoldButton();
    },

    "makeProductDescription": function(productContent) {
        $(".store_details > .dsc").text(productContent);
    },

    "makeEventDescription": function(productPrices) {
        const eventContent = [];

        productPrices.forEach(function(price) {
            if(price.discountRate !== 0) {
                eventContent.push(price.priceTypeName + "석 " + price.discountRate + "%");
            }
        });

        if (eventContent.length === 0) {
            $("#event_description").text("이벤트가 없습니다.");
        } else {
            $("#event_description").append("[네이버예약 특별할인]<br>" + eventContent.join(", ") + " 할인");
        }
    },

    "setReserveButton": function() {
        $("#reserve_button").attr("href", "reserve?displayInfoId=" + getParameter("displayInfoId"));
    },

    "activateFoldButton": function() {
        $("a.bk_more._open, a.bk_more._close").on("click", function() {
            $("a.bk_more._open, a.bk_more._close").toggle();
            $("div.store_details").toggleClass("close3");
        });
    },
};

function DetailTabView() {
}
DetailTabView.prototype = {
    "init": function(displayInfo, displayInfoImage) {
        this.makeDetailTabContent(displayInfo, displayInfoImage);
        this.activateTabButton();
    },

    "makeDetailTabContent": function(displayInfo, displayInfoImage) {
        $("#detail_content").text(displayInfo.productContent);
        $("#store_name").text(displayInfo.categoryName + displayInfo.productDescription);
        $("#store_addr").text(displayInfo.placeStreet);
        $("#store_old_addr").text(displayInfo.placeLot);
        $("#store_addr_detail").text(displayInfo.placeName);
        $("#store_tel").text(displayInfo.telephone);
        $("#store_map").attr("src", "/reservation/download/image/" + displayInfoImage.fileId);
    },

    "activateTabButton": function() {
        $("#detail_tab").on("click", ".item", function() {
            if ($(this).hasClass("active")) {
                return;
            } 

            $("#detail_tab .item").toggleClass("active");
            $("#detail_tab .anchor").toggleClass("active");
            $(".detail_area_wrap, .detail_location").toggleClass("hide");
        });
    },
};