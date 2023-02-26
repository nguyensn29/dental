import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IUserPayment, UserPayment } from '@/shared/model/user-payment.model';
import UserPaymentService from './user-payment.service';

const validations: any = {
  userPayment: {
    userId: {
      required,
      numeric,
    },
    name: {},
    phone: {},
    adress: {},
    provincial: {},
    district: {},
    ward: {},
    email: {},
    street: {},
  },
};

@Component({
  validations,
})
export default class UserPaymentUpdate extends Vue {
  @Inject('userPaymentService') private userPaymentService: () => UserPaymentService;
  @Inject('alertService') private alertService: () => AlertService;

  public userPayment: IUserPayment = new UserPayment();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userPaymentId) {
        vm.retrieveUserPayment(to.params.userPaymentId);
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
    if (this.userPayment.id) {
      this.userPaymentService()
        .update(this.userPayment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.userPayment.updated', { param: param.id });
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
      this.userPaymentService()
        .create(this.userPayment)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.userPayment.created', { param: param.id });
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

  public retrieveUserPayment(userPaymentId): void {
    this.userPaymentService()
      .find(userPaymentId)
      .then(res => {
        this.userPayment = res;
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
