import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { ISetting, Setting } from '@/shared/model/setting.model';
import SettingService from './setting.service';

const validations: any = {
  setting: {
    name: {
      required,
    },
    keyName: {},
    value: {},
    rule: {},
    isNumber: {
      required,
      numeric,
    },
    isObject: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class SettingUpdate extends Vue {
  @Inject('settingService') private settingService: () => SettingService;
  @Inject('alertService') private alertService: () => AlertService;

  public setting: ISetting = new Setting();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.settingId) {
        vm.retrieveSetting(to.params.settingId);
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
    if (this.setting.id) {
      this.settingService()
        .update(this.setting)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.setting.updated', { param: param.id });
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
      this.settingService()
        .create(this.setting)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('dentalApp.setting.created', { param: param.id });
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

  public retrieveSetting(settingId): void {
    this.settingService()
      .find(settingId)
      .then(res => {
        this.setting = res;
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
