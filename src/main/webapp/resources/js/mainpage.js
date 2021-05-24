$(document).ready(function() {
    const mainPageLoader = new MainPageLoader();
    mainPageLoader.initPage();
});

function MainPageLoader() {
}
MainPageLoader.prototype = {
    "initPage": function() {
        const sliderView = new SliderView();
        const categoryTabView = new CategoryTabView();
        const productView = new ProductView();

        sliderView.init();
        categoryTabView.init();
        productView.init();
    }
}

function SliderView() {
}
SliderView.prototype = {
    "init": function() {
        this.requestSliderImage();
    },

    "requestSliderImage": function() {
        $.ajax({
            type: "GET",
            url: "/reservation/api/promotions",
        })
        .done(function(data) {
            this.makeSliderImage(data);
            this.runSlider();
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "makeSliderImage": function(promotionData) {
        const $slider = $("#main_slider");
        const promotionTemplate = $("#promotion_item_template").text();
        const handlebarsTemplate = Handlebars.compile(promotionTemplate);
    
        $slider.append(handlebarsTemplate(promotionData));
        $slider.append($slider.children().first().clone(true));
    },

    "runSlider": function() {
        const $slider = $("#main_slider");
        const sliderLength = $slider.children().length - 1;
        const imageWidth = $slider.css("width").replace("px", "");
        
        let index = 0;
        setInterval(function() {
            index = (index % sliderLength) + 1;

            $slider.css({
                "transition": "800ms",
                "transform": `translate(-${imageWidth * index}px, 0)`
            });

            if (index === sliderLength) {
                setTimeout(() => {
                    $slider.css({
                        "transition": "0ms",
                        "transform": `translate(0, 0)`
                    });
                }, 1000);
            }
        }, 2500);
    }
}

function CategoryTabView() {
}
CategoryTabView.prototype = {
    "init": function() {
        this.requestCategoryTab();
        this.activateCategoryTabSwitch();
    },

    "requestCategoryTab": function() {
        $.ajax({
            type: "GET",
            url: "/reservation/api/categories",
        })
        .done(function(data) {
            this.makeCategoryTab(data);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },

    "makeCategoryTab": function(categoryData) {
        const categoryTemplate = $("#category_item_template").text();
        const handlebarsTemplate = Handlebars.compile(categoryTemplate);
    
        $("#category_tab").append(handlebarsTemplate(categoryData));
    },

    "activateCategoryTabSwitch": function() {
        const productView = new ProductView(); 
        const categoryTab = $("#category_tab");
        
        const self = this;
        categoryTab.on("click", ".anchor", function() {
            const $currentCategory = $("#category_tab .anchor.active");
            const $targetCategory = $(this);
            const categoryId = $targetCategory.parent().data("category");

            $("#left_product_box").empty();
            $("#right_product_box").empty();

            self.changeCategoryTabActive($currentCategory, $targetCategory);
            productView.updateProductBox(categoryId);
        });
    },

    "changeCategoryTabActive": function($currentCategory, $targetCategory) {
        $currentCategory.removeClass("active");
        $targetCategory.addClass("active");
    }
}

function ProductView() {
}
ProductView.prototype = {
    "init": function() {
        this.updateProductBox(0);
        this.activateMoreButton();
    },

    "updateProductBox": function(categoryId) {
        const currentProductCount = $(".lst_event_box .item").length;

        $.ajax({
            type: "GET",
            url: `/reservation/api/products?categoryId=${categoryId}&start=${currentProductCount}`,
        })
        .done(function(data) {
            const productTotalCount = data.totalCount;
            const productItemList = data.items;
            
            const productTemplate = $("#product_item_template").text();
            const handlebarsTemplate = Handlebars.compile(productTemplate);
            
            for(let index in productItemList) {
                const item = productItemList[index];
                
                if (index % 2 == 0) {
                    $("#left_product_box").append(handlebarsTemplate(item));
                } else {
                    $("#right_product_box").append(handlebarsTemplate(item));
                }
            }

            $("#category_item_count").text(productTotalCount);
    
            this.checkMoreButton(currentProductCount + productItemList.length, productTotalCount);
        }.bind(this))
        .fail(function(response) {
            alert(`에러발생 : ${response.status} - ${response.responseText}`);
        });
    },
    
    "checkMoreButton": function(currentProductCount, productTotalCount) {
        if(currentProductCount >= productTotalCount) {
            $("#more_button").hide();
        } else {
            $("#more_button").show();
        }
    },
    
    "activateMoreButton": function() {
        $("#more_button").on("click", function() {
            const $currentCategory = $("#category_tab .anchor.active");
            const categoryId = $currentCategory.parent().data("category");
            
            this.updateProductBox(categoryId);
        }.bind(this));
    }
}