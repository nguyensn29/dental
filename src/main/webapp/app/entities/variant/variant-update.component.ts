import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, decimal } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IVariant, Variant } from '@/shared/model/variant.model';
import VariantService from './variant.service';

const validations: any = {
  variant: {
    productId: {
      required,
      numeric,
    },
    shopifyId: {},
    title: {
      required,
    },
    price: {
      required,
      decimal,
    },
    discount: {
      required,
      decimal,
    },
    option1: {},
    option2: {},
    option3: {},
    imageId: {},
    weight: {
      required,
      numeric,
    },
    compareAtPrice: {},
  },
};

@Component({
  validations,
})
export default class VariantUpdate extends Vue {
  @Inject('variantService') private variantService: () => VariantService;
  @Inject('alertService') private alertService: () => AlertService;

  public variant: IVariant = new Variant();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.variantId) {
        vm.retrieveVariant(to.params.variantId);
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
    if (this.variant.id) {
      this.variantService()
        .update(this.variant)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.variant.updated', { param: param.id });
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
      this.variantService()
        .create(this.variant)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.variant.created', { param: param.id });
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

  public retrieveVariant(variantId): void {
    this.variantService()
      .find(variantId)
      .then(res => {
        this.variant = res;
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
