import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVendor } from '@/shared/model/vendor.model';
import VendorService from './vendor.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VendorDetails extends Vue {
  @Inject('vendorService') private vendorService: () => VendorService;
  @Inject('alertService') private alertService: () => AlertService;

  public vendor: IVendor = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vendorId) {
        vm.retrieveVendor(to.params.vendorId);
      }
    });
  }

  public retrieveVendor(vendorId) {
    this.vendorService()
      .find(vendorId)
      .then(res => {
        this.vendor = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
