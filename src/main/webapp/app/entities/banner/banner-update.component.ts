import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IBanner, Banner } from '@/shared/model/banner.model';
import BannerService from './banner.service';

const validations: any = {
  banner: {
    name: {
      required,
    },
    src: {
      required,
    },
    isShow: {
      required,
      numeric,
    },
    imgPublicId: {},
  },
};

@Component({
  validations,
})
export default class BannerUpdate extends Vue {
  @Inject('bannerService') private bannerService: () => BannerService;
  @Inject('alertService') private alertService: () => AlertService;

  public banner: IBanner = new Banner();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bannerId) {
        vm.retrieveBanner(to.params.bannerId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.banner.id) {
      this.bannerService()
        .update(this.banner)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.banner.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.bannerService()
        .create(this.banner)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.banner.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveBanner(bannerId): void {
    this.bannerService()
      .find(bannerId)
      .then(res => {
        this.banner = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
