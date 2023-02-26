import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, decimal } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IOrderDetails, OrderDetails } from '@/shared/model/order-details.model';
import OrderDetailsService from './order-details.service';

const validations: any = {
  orderDetails: {
    orderId: {
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
    avatar: {},
    name: {},
    price: {
      required,
      decimal,
    },
    point: {
      required,
      numeric,
    },
    qty: {
      required,
      numeric,
    },
    discount: {
      required,
      decimal,
    },
    subtotalAmount: {
      required,
      decimal,
    },
    totalAmount: {
      required,
      decimal,
    },
    variantId: {},
    shopifyVariantId: {},
    shopifyProductId: {},
    shopifyOrderId: {},
    shopifyId: {},
  },
};

@Component({
  validations,
})
export default class OrderDetailsUpdate extends Vue {
  @Inject('orderDetailsService') private orderDetailsService: () => OrderDetailsService;
  @Inject('alertService') private alertService: () => AlertService;

  public orderDetails: IOrderDetails = new OrderDetails();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.orderDetailsId) {
        vm.retrieveOrderDetails(to.params.orderDetailsId);
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
    if (this.orderDetails.id) {
      this.orderDetailsService()
        .update(this.orderDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.orderDetails.updated', { param: param.id });
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
      this.orderDetailsService()
        .create(this.orderDetails)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.orderDetails.created', { param: param.id });
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

  public retrieveOrderDetails(orderDetailsId): void {
    this.orderDetailsService()
      .find(orderDetailsId)
      .then(res => {
        this.orderDetails = res;
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
