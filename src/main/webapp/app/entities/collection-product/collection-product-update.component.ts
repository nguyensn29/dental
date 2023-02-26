import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { ICollectionProduct, CollectionProduct } from '@/shared/model/collection-product.model';
import CollectionProductService from './collection-product.service';

const validations: any = {
  collectionProduct: {
    collectionId: {
      required,
      numeric,
    },
    productId: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class CollectionProductUpdate extends Vue {
  @Inject('collectionProductService') private collectionProductService: () => CollectionProductService;
  @Inject('alertService') private alertService: () => AlertService;

  public collectionProduct: ICollectionProduct = new CollectionProduct();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.collectionProductId) {
        vm.retrieveCollectionProduct(to.params.collectionProductId);
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
    if (this.collectionProduct.id) {
      this.collectionProductService()
        .update(this.collectionProduct)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.collectionProduct.updated', { param: param.id });
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
      this.collectionProductService()
        .create(this.collectionProduct)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.collectionProduct.created', { param: param.id });
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

  public retrieveCollectionProduct(collectionProductId): void {
    this.collectionProductService()
      .find(collectionProductId)
      .then(res => {
        this.collectionProduct = res;
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
