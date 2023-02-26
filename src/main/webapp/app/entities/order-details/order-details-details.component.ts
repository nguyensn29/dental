import { Component, Vue, Inject } from 'vue-property-decorator';

import { IOrderDetails } from '@/shared/model/order-details.model';
import OrderDetailsService from './order-details.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class OrderDetailsDetails extends Vue {
  @Inject('orderDetailsService') private orderDetailsService: () => OrderDetailsService;
  @Inject('alertService') private alertService: () => AlertService;

  public orderDetails: IOrderDetails = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.orderDetailsId) {
        vm.retrieveOrderDetails(to.params.orderDetailsId);
      }
    });
  }

  public retrieveOrderDetails(orderDetailsId) {
    this.orderDetailsService()
      .find(orderDetailsId)
      .then(res => {
        this.orderDetails = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
