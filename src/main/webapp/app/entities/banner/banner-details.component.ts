import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBanner } from '@/shared/model/banner.model';
import BannerService from './banner.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class BannerDetails extends Vue {
  @Inject('bannerService') private bannerService: () => BannerService;
  @Inject('alertService') private alertService: () => AlertService;

  public banner: IBanner = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bannerId) {
        vm.retrieveBanner(to.params.bannerId);
      }
    });
  }

  public retrieveBanner(bannerId) {
    this.bannerService()
      .find(bannerId)
      .then(res => {
        this.banner = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
