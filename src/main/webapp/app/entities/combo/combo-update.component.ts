import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, decimal, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { ICombo, Combo } from '@/shared/model/combo.model';
import ComboService from './combo.service';

const validations: any = {
  combo: {
    name: {
      required,
    },
    price: {
      required,
      decimal,
    },
    discount: {},
    weight: {
      required,
      numeric,
    },
    point: {
      required,
      numeric,
    },
    description: {},
    liked: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class ComboUpdate extends Vue {
  @Inject('comboService') private comboService: () => ComboService;
  @Inject('alertService') private alertService: () => AlertService;

  public combo: ICombo = new Combo();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.comboId) {
        vm.retrieveCombo(to.params.comboId);
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
    if (this.combo.id) {
      this.comboService()
        .update(this.combo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.combo.updated', { param: param.id });
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
      this.comboService()
        .create(this.combo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.combo.created', { param: param.id });
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

  public retrieveCombo(comboId): void {
    this.comboService()
      .find(comboId)
      .then(res => {
        this.combo = res;
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
