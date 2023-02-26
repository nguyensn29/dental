import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { ICart, Cart } from '@/shared/model/cart.model';
import CartService from './cart.service';

const validations: any = {
  cart: {
    userId: {
      required,
      numeric,
    },
    productId: {
      required,
      numeric,
    },
    variantId: {},
    productType: {
      required,
      numeric,
    },
    qty: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class CartUpdate extends Vue {
  @Inject('cartService') private cartService: () => CartService;
  @Inject('alertService') private alertService: () => AlertService;

  public cart: ICart = new Cart();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.cartId) {
        vm.retrieveCart(to.params.cartId);
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
    if (this.cart.id) {
      this.cartService()
        .update(this.cart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.cart.updated', { param: param.id });
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
      this.cartService()
        .create(this.cart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.cart.created', { param: param.id });
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

  public retrieveCart(cartId): void {
    this.cartService()
      .find(cartId)
      .then(res => {
        this.cart = res;
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
