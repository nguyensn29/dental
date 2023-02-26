import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProductUser } from '@/shared/model/product-user.model';
import ProductUserService from './product-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ProductUserDetails extends Vue {
  @Inject('productUserService') private productUserService: () => ProductUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public productUser: IProductUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productUserId) {
        vm.retrieveProductUser(to.params.productUserId);
      }
    });
  }

  public retrieveProductUser(productUserId) {
    this.productUserService()
      .find(productUserId)
      .then(res => {
        this.productUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
