import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IVendor } from '@/shared/model/vendor.model';

import VendorService from './vendor.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Vendor extends Vue {
  @Inject('vendorService') private vendorService: () => VendorService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public vendors: IVendor[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllVendors();
  }

  public clear(): void {
    this.retrieveAllVendors();
  }

  public retrieveAllVendors(): void {
    this.isFetching = true;
    this.vendorService()
      .retrieve()
      .then(
        res => {
          this.vendors = res.data;
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

  public prepareRemove(instance: IVendor): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeVendor(): void {
    this.vendorService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.vendor.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllVendors();
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
