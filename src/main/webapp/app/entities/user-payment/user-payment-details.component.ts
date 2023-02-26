import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUserPayment } from '@/shared/model/user-payment.model';
import UserPaymentService from './user-payment.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class UserPaymentDetails extends Vue {
  @Inject('userPaymentService') private userPaymentService: () => UserPaymentService;
  @Inject('alertService') private alertService: () => AlertService;

  public userPayment: IUserPayment = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userPaymentId) {
        vm.retrieveUserPayment(to.params.userPaymentId);
      }
    });
  }

  public retrieveUserPayment(userPaymentId) {
    this.userPaymentService()
      .find(userPaymentId)
      .then(res => {
        this.userPayment = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
