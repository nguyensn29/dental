import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IProductUser, ProductUser } from '@/shared/model/product-user.model';
import ProductUserService from './product-user.service';

const validations: any = {
  productUser: {
    userId: {
      required,
      numeric,
    },
    productId: {
      required,
      numeric,
    },
    productType: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class ProductUserUpdate extends Vue {
  @Inject('productUserService') private productUserService: () => ProductUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public productUser: IProductUser = new ProductUser();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productUserId) {
        vm.retrieveProductUser(to.params.productUserId);
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
    if (this.productUser.id) {
      this.productUserService()
        .update(this.productUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.productUser.updated', { param: param.id });
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
      this.productUserService()
        .create(this.productUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.productUser.created', { param: param.id });
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

  public retrieveProductUser(productUserId): void {
    this.productUserService()
      .find(productUserId)
      .then(res => {
        this.productUser = res;
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
