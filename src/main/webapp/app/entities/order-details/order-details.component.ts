import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IOrderDetails } from '@/shared/model/order-details.model';

import OrderDetailsService from './order-details.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class OrderDetails extends Vue {
  @Inject('orderDetailsService') private orderDetailsService: () => OrderDetailsService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public orderDetails: IOrderDetails[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllOrderDetailss();
  }

  public clear(): void {
    this.retrieveAllOrderDetailss();
  }

  public retrieveAllOrderDetailss(): void {
    this.isFetching = true;
    this.orderDetailsService()
      .retrieve()
      .then(
        res => {
          this.orderDetails = res.data;
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

  public prepareRemove(instance: IOrderDetails): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeOrderDetails(): void {
    this.orderDetailsService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.orderDetails.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllOrderDetailss();
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
