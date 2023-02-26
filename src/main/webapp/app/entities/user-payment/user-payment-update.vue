<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.userPayment.home.createOrEditLabel"
          data-cy="UserPaymentCreateUpdateHeading"
          v-text="$t('dentalApp.userPayment.home.createOrEditLabel')"
        >
          Create or edit a UserPayment
        </h2>
        <div>
          <div class="form-group" v-if="userPayment.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="userPayment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.userId')" for="user-payment-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="user-payment-userId"
              data-cy="userId"
              :class="{ valid: !$v.userPayment.userId.$invalid, invalid: $v.userPayment.userId.$invalid }"
              v-model.number="$v.userPayment.userId.$model"
              required
            />
            <div v-if="$v.userPayment.userId.$anyDirty && $v.userPayment.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.userPayment.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.userPayment.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.name')" for="user-payment-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="user-payment-name"
              data-cy="name"
              :class="{ valid: !$v.userPayment.name.$invalid, invalid: $v.userPayment.name.$invalid }"
              v-model="$v.userPayment.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.phone')" for="user-payment-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="user-payment-phone"
              data-cy="phone"
              :class="{ valid: !$v.userPayment.phone.$invalid, invalid: $v.userPayment.phone.$invalid }"
              v-model="$v.userPayment.phone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.adress')" for="user-payment-adress">Adress</label>
            <input
              type="text"
              class="form-control"
              name="adress"
              id="user-payment-adress"
              data-cy="adress"
              :class="{ valid: !$v.userPayment.adress.$invalid, invalid: $v.userPayment.adress.$invalid }"
              v-model="$v.userPayment.adress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.provincial')" for="user-payment-provincial"
              >Provincial</label
            >
            <input
              type="text"
              class="form-control"
              name="provincial"
              id="user-payment-provincial"
              data-cy="provincial"
              :class="{ valid: !$v.userPayment.provincial.$invalid, invalid: $v.userPayment.provincial.$invalid }"
              v-model="$v.userPayment.provincial.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.district')" for="user-payment-district">District</label>
            <input
              type="text"
              class="form-control"
              name="district"
              id="user-payment-district"
              data-cy="district"
              :class="{ valid: !$v.userPayment.district.$invalid, invalid: $v.userPayment.district.$invalid }"
              v-model="$v.userPayment.district.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.ward')" for="user-payment-ward">Ward</label>
            <input
              type="text"
              class="form-control"
              name="ward"
              id="user-payment-ward"
              data-cy="ward"
              :class="{ valid: !$v.userPayment.ward.$invalid, invalid: $v.userPayment.ward.$invalid }"
              v-model="$v.userPayment.ward.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.email')" for="user-payment-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="user-payment-email"
              data-cy="email"
              :class="{ valid: !$v.userPayment.email.$invalid, invalid: $v.userPayment.email.$invalid }"
              v-model="$v.userPayment.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.userPayment.street')" for="user-payment-street">Street</label>
            <input
              type="text"
              class="form-control"
              name="street"
              id="user-payment-street"
              data-cy="street"
              :class="{ valid: !$v.userPayment.street.$invalid, invalid: $v.userPayment.street.$invalid }"
              v-model="$v.userPayment.street.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.userPayment.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./user-payment-update.component.ts"></script>
