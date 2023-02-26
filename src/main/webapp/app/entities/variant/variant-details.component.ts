import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVariant } from '@/shared/model/variant.model';
import VariantService from './variant.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VariantDetails extends Vue {
  @Inject('variantService') private variantService: () => VariantService;
  @Inject('alertService') private alertService: () => AlertService;

  public variant: IVariant = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.variantId) {
        vm.retrieveVariant(to.params.variantId);
      }
    });
  }

  public retrieveVariant(variantId) {
    this.variantService()
      .find(variantId)
      .then(res => {
        this.variant = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
