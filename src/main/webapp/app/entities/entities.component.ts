import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import BannerService from './banner/banner.service';
import BlogService from './blog/blog.service';
import CartService from './cart/cart.service';
import CategoryService from './category/category.service';
import CollectionService from './collection/collection.service';
import ProductService from './product/product.service';
import CollectionProductService from './collection-product/collection-product.service';
import ComboService from './combo/combo.service';
import ImageService from './image/image.service';
import MessageService from './message/message.service';
import MigrationService from './migration/migration.service';
import OrderService from './order/order.service';
import OrderDetailsService from './order-details/order-details.service';
import ProductUserService from './product-user/product-user.service';
import RankService from './rank/rank.service';
import SettingService from './setting/setting.service';
import UserPaymentService from './user-payment/user-payment.service';
import VariantService from './variant/variant.service';
import VendorService from './vendor/vendor.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('bannerService') private bannerService = () => new BannerService();
  @Provide('blogService') private blogService = () => new BlogService();
  @Provide('cartService') private cartService = () => new CartService();
  @Provide('categoryService') private categoryService = () => new CategoryService();
  @Provide('collectionService') private collectionService = () => new CollectionService();
  @Provide('productService') private productService = () => new ProductService();
  @Provide('collectionProductService') private collectionProductService = () => new CollectionProductService();
  @Provide('comboService') private comboService = () => new ComboService();
  @Provide('imageService') private imageService = () => new ImageService();
  @Provide('messageService') private messageService = () => new MessageService();
  @Provide('migrationService') private migrationService = () => new MigrationService();
  @Provide('orderService') private orderService = () => new OrderService();
  @Provide('orderDetailsService') private orderDetailsService = () => new OrderDetailsService();
  @Provide('productUserService') private productUserService = () => new ProductUserService();
  @Provide('rankService') private rankService = () => new RankService();
  @Provide('settingService') private settingService = () => new SettingService();
  @Provide('userPaymentService') private userPaymentService = () => new UserPaymentService();
  @Provide('variantService') private variantService = () => new VariantService();
  @Provide('vendorService') private vendorService = () => new VendorService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
