import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBanner } from '@/shared/model/banner.model';

import BannerService from './banner.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Banner extends Vue {
  @Inject('bannerService') private bannerService: () => BannerService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public banners: IBanner[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBanners();
  }

  public clear(): void {
    this.retrieveAllBanners();
  }

  public retrieveAllBanners(): void {
    this.isFetching = true;
    this.bannerService()
      .retrieve()
      .then(
        res => {
          this.banners = res.data;
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

  public prepareRemove(instance: IBanner): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeBanner(): void {
    this.bannerService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('dentalApp.banner.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllBanners();
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
