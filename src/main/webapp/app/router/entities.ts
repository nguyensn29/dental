import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Banner = () => import('@/entities/banner/banner.vue');
// prettier-ignore
const BannerUpdate = () => import('@/entities/banner/banner-update.vue');
// prettier-ignore
const BannerDetails = () => import('@/entities/banner/banner-details.vue');
// prettier-ignore
const Blog = () => import('@/entities/blog/blog.vue');
// prettier-ignore
const BlogUpdate = () => import('@/entities/blog/blog-update.vue');
// prettier-ignore
const BlogDetails = () => import('@/entities/blog/blog-details.vue');
// prettier-ignore
const Cart = () => import('@/entities/cart/cart.vue');
// prettier-ignore
const CartUpdate = () => import('@/entities/cart/cart-update.vue');
// prettier-ignore
const CartDetails = () => import('@/entities/cart/cart-details.vue');
// prettier-ignore
const Category = () => import('@/entities/category/category.vue');
// prettier-ignore
const CategoryUpdate = () => import('@/entities/category/category-update.vue');
// prettier-ignore
const CategoryDetails = () => import('@/entities/category/category-details.vue');
// prettier-ignore
const Collection = () => import('@/entities/collection/collection.vue');
// prettier-ignore
const CollectionUpdate = () => import('@/entities/collection/collection-update.vue');
// prettier-ignore
const CollectionDetails = () => import('@/entities/collection/collection-details.vue');
// prettier-ignore
const Product = () => import('@/entities/product/product.vue');
// prettier-ignore
const ProductUpdate = () => import('@/entities/product/product-update.vue');
// prettier-ignore
const ProductDetails = () => import('@/entities/product/product-details.vue');
// prettier-ignore
const CollectionProduct = () => import('@/entities/collection-product/collection-product.vue');
// prettier-ignore
const CollectionProductUpdate = () => import('@/entities/collection-product/collection-product-update.vue');
// prettier-ignore
const CollectionProductDetails = () => import('@/entities/collection-product/collection-product-details.vue');
// prettier-ignore
const Combo = () => import('@/entities/combo/combo.vue');
// prettier-ignore
const ComboUpdate = () => import('@/entities/combo/combo-update.vue');
// prettier-ignore
const ComboDetails = () => import('@/entities/combo/combo-details.vue');
// prettier-ignore
const Image = () => import('@/entities/image/image.vue');
// prettier-ignore
const ImageUpdate = () => import('@/entities/image/image-update.vue');
// prettier-ignore
const ImageDetails = () => import('@/entities/image/image-details.vue');
// prettier-ignore
const Message = () => import('@/entities/message/message.vue');
// prettier-ignore
const MessageUpdate = () => import('@/entities/message/message-update.vue');
// prettier-ignore
const MessageDetails = () => import('@/entities/message/message-details.vue');
// prettier-ignore
const Migration = () => import('@/entities/migration/migration.vue');
// prettier-ignore
const MigrationUpdate = () => import('@/entities/migration/migration-update.vue');
// prettier-ignore
const MigrationDetails = () => import('@/entities/migration/migration-details.vue');
// prettier-ignore
const Order = () => import('@/entities/order/order.vue');
// prettier-ignore
const OrderUpdate = () => import('@/entities/order/order-update.vue');
// prettier-ignore
const OrderDetails = () => import('@/entities/order/order-details.vue');
// prettier-ignore
const OrderDetails = () => import('@/entities/order-details/order-details.vue');
// prettier-ignore
const OrderDetailsUpdate = () => import('@/entities/order-details/order-details-update.vue');
// prettier-ignore
const OrderDetailsDetails = () => import('@/entities/order-details/order-details-details.vue');
// prettier-ignore
const ProductUser = () => import('@/entities/product-user/product-user.vue');
// prettier-ignore
const ProductUserUpdate = () => import('@/entities/product-user/product-user-update.vue');
// prettier-ignore
const ProductUserDetails = () => import('@/entities/product-user/product-user-details.vue');
// prettier-ignore
const Rank = () => import('@/entities/rank/rank.vue');
// prettier-ignore
const RankUpdate = () => import('@/entities/rank/rank-update.vue');
// prettier-ignore
const RankDetails = () => import('@/entities/rank/rank-details.vue');
// prettier-ignore
const Setting = () => import('@/entities/setting/setting.vue');
// prettier-ignore
const SettingUpdate = () => import('@/entities/setting/setting-update.vue');
// prettier-ignore
const SettingDetails = () => import('@/entities/setting/setting-details.vue');
// prettier-ignore
const UserPayment = () => import('@/entities/user-payment/user-payment.vue');
// prettier-ignore
const UserPaymentUpdate = () => import('@/entities/user-payment/user-payment-update.vue');
// prettier-ignore
const UserPaymentDetails = () => import('@/entities/user-payment/user-payment-details.vue');
// prettier-ignore
const Variant = () => import('@/entities/variant/variant.vue');
// prettier-ignore
const VariantUpdate = () => import('@/entities/variant/variant-update.vue');
// prettier-ignore
const VariantDetails = () => import('@/entities/variant/variant-details.vue');
// prettier-ignore
const Vendor = () => import('@/entities/vendor/vendor.vue');
// prettier-ignore
const VendorUpdate = () => import('@/entities/vendor/vendor-update.vue');
// prettier-ignore
const VendorDetails = () => import('@/entities/vendor/vendor-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'banner',
      name: 'Banner',
      component: Banner,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'banner/new',
      name: 'BannerCreate',
      component: BannerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'banner/:bannerId/edit',
      name: 'BannerEdit',
      component: BannerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'banner/:bannerId/view',
      name: 'BannerView',
      component: BannerDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'blog',
      name: 'Blog',
      component: Blog,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'blog/new',
      name: 'BlogCreate',
      component: BlogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'blog/:blogId/edit',
      name: 'BlogEdit',
      component: BlogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'blog/:blogId/view',
      name: 'BlogView',
      component: BlogDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart',
      name: 'Cart',
      component: Cart,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/new',
      name: 'CartCreate',
      component: CartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/:cartId/edit',
      name: 'CartEdit',
      component: CartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/:cartId/view',
      name: 'CartView',
      component: CartDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category',
      name: 'Category',
      component: Category,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/new',
      name: 'CategoryCreate',
      component: CategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/:categoryId/edit',
      name: 'CategoryEdit',
      component: CategoryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'category/:categoryId/view',
      name: 'CategoryView',
      component: CategoryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection',
      name: 'Collection',
      component: Collection,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection/new',
      name: 'CollectionCreate',
      component: CollectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection/:collectionId/edit',
      name: 'CollectionEdit',
      component: CollectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection/:collectionId/view',
      name: 'CollectionView',
      component: CollectionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product',
      name: 'Product',
      component: Product,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product/new',
      name: 'ProductCreate',
      component: ProductUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product/:productId/edit',
      name: 'ProductEdit',
      component: ProductUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product/:productId/view',
      name: 'ProductView',
      component: ProductDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection-product',
      name: 'CollectionProduct',
      component: CollectionProduct,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection-product/new',
      name: 'CollectionProductCreate',
      component: CollectionProductUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection-product/:collectionProductId/edit',
      name: 'CollectionProductEdit',
      component: CollectionProductUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'collection-product/:collectionProductId/view',
      name: 'CollectionProductView',
      component: CollectionProductDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'combo',
      name: 'Combo',
      component: Combo,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'combo/new',
      name: 'ComboCreate',
      component: ComboUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'combo/:comboId/edit',
      name: 'ComboEdit',
      component: ComboUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'combo/:comboId/view',
      name: 'ComboView',
      component: ComboDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'image',
      name: 'Image',
      component: Image,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'image/new',
      name: 'ImageCreate',
      component: ImageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'image/:imageId/edit',
      name: 'ImageEdit',
      component: ImageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'image/:imageId/view',
      name: 'ImageView',
      component: ImageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message',
      name: 'Message',
      component: Message,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message/new',
      name: 'MessageCreate',
      component: MessageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message/:messageId/edit',
      name: 'MessageEdit',
      component: MessageUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'message/:messageId/view',
      name: 'MessageView',
      component: MessageDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'migration',
      name: 'Migration',
      component: Migration,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'migration/new',
      name: 'MigrationCreate',
      component: MigrationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'migration/:migrationId/edit',
      name: 'MigrationEdit',
      component: MigrationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'migration/:migrationId/view',
      name: 'MigrationView',
      component: MigrationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order',
      name: 'Order',
      component: Order,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order/new',
      name: 'OrderCreate',
      component: OrderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order/:orderId/edit',
      name: 'OrderEdit',
      component: OrderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order/:orderId/view',
      name: 'OrderView',
      component: OrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-details',
      name: 'OrderDetails',
      component: OrderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-details/new',
      name: 'OrderDetailsCreate',
      component: OrderDetailsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-details/:orderDetailsId/edit',
      name: 'OrderDetailsEdit',
      component: OrderDetailsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-details/:orderDetailsId/view',
      name: 'OrderDetailsView',
      component: OrderDetailsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-user',
      name: 'ProductUser',
      component: ProductUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-user/new',
      name: 'ProductUserCreate',
      component: ProductUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-user/:productUserId/edit',
      name: 'ProductUserEdit',
      component: ProductUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-user/:productUserId/view',
      name: 'ProductUserView',
      component: ProductUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rank',
      name: 'Rank',
      component: Rank,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rank/new',
      name: 'RankCreate',
      component: RankUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rank/:rankId/edit',
      name: 'RankEdit',
      component: RankUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'rank/:rankId/view',
      name: 'RankView',
      component: RankDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting',
      name: 'Setting',
      component: Setting,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/new',
      name: 'SettingCreate',
      component: SettingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/:settingId/edit',
      name: 'SettingEdit',
      component: SettingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'setting/:settingId/view',
      name: 'SettingView',
      component: SettingDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-payment',
      name: 'UserPayment',
      component: UserPayment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-payment/new',
      name: 'UserPaymentCreate',
      component: UserPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-payment/:userPaymentId/edit',
      name: 'UserPaymentEdit',
      component: UserPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'user-payment/:userPaymentId/view',
      name: 'UserPaymentView',
      component: UserPaymentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'variant',
      name: 'Variant',
      component: Variant,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'variant/new',
      name: 'VariantCreate',
      component: VariantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'variant/:variantId/edit',
      name: 'VariantEdit',
      component: VariantUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'variant/:variantId/view',
      name: 'VariantView',
      component: VariantDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vendor',
      name: 'Vendor',
      component: Vendor,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vendor/new',
      name: 'VendorCreate',
      component: VendorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vendor/:vendorId/edit',
      name: 'VendorEdit',
      component: VendorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vendor/:vendorId/view',
      name: 'VendorView',
      component: VendorDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
