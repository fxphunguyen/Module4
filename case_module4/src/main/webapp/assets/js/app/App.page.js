class AppPage {
    static renderProductItem(obj) {
        return `
            <li class="main-product">
                    <div class="img-product">
                        <img class="img-prd"
                            src="${obj.image}"
                            alt="">
                    </div>
                    <div class="content-product">
                        <h3 class="content-product-h3">${obj.name}</h3>
                        <div class="content-product-deltals">
                            <div class="price">
                                <span class="money">${new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(obj.price)}
                                </span>
                            </div>
                            <button type="button" class="btn btn-cart">Thêm Vào Giỏ</button>
                        </div>
                    </div>
                </li>
        `;
    }
}