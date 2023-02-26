import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICollectionProduct } from '@/shared/model/collection-product.model';
import CollectionProductService from './collection-product.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CollectionProductDetails extends Vue {
  @Inject('collectionProductService') private collectionProductService: () => CollectionProductService;
  @Inject('alertService') private alertService: () => AlertService;

  public collectionProduct: ICollectionProduct = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.collectionProductId) {
        vm.retrieveCollectionProduct(to.params.collectionProductId);
      }
    });
  }

  public retrieveCollectionProduct(collectionProductId) {
    this.collectionProductService()
      .find(collectionProductId)
      .then(res => {
        this.collectionProduct = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
