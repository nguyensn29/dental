import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserPayment } from '@/shared/model/user-payment.model';

import UserPaymentService from './user-payment.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UserPayment extends Vue {
  @Inject('userPaymentService') private userPaymentService: () => UserPaymentService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public userPayments: IUserPayment[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserPayments();
  }

  public clear(): void {
    this.retrieveAllUserPayments();
  }

  public retrieveAllUserPayments(): void {
    this.isFetching = true;
    this.userPaymentService()
      .retrieve()
      .then(
        res => {
          this.userPayments = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IUserPayment): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserPayment(): void {
    this.userPaymentService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.userPayment.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserPayments();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
