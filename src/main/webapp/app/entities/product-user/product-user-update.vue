<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.productUser.home.createOrEditLabel"
          data-cy="ProductUserCreateUpdateHeading"
          v-text="$t('dentalApp.productUser.home.createOrEditLabel')"
        >
          Create or edit a ProductUser
        </h2>
        <div>
          <div class="form-group" v-if="productUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="productUser.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.productUser.userId')" for="product-user-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="product-user-userId"
              data-cy="userId"
              :class="{ valid: !$v.productUser.userId.$invalid, invalid: $v.productUser.userId.$invalid }"
              v-model.number="$v.productUser.userId.$model"
              required
            />
            <div v-if="$v.productUser.userId.$anyDirty && $v.productUser.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.productUser.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.productUser.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.productUser.productId')" for="product-user-productId">Product Id</label>
            <input
              type="number"
              class="form-control"
              name="productId"
              id="product-user-productId"
              data-cy="productId"
              :class="{ valid: !$v.productUser.productId.$invalid, invalid: $v.productUser.productId.$invalid }"
              v-model.number="$v.productUser.productId.$model"
              required
            />
            <div v-if="$v.productUser.productId.$anyDirty && $v.productUser.productId.$invalid">
              <small class="form-text text-danger" v-if="!$v.productUser.productId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.productUser.productId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.productUser.productType')" for="product-user-productType"
              >Product Type</label
            >
            <input
              type="number"
              class="form-control"
              name="productType"
              id="product-user-productType"
              data-cy="productType"
              :class="{ valid: !$v.productUser.productType.$invalid, invalid: $v.productUser.productType.$invalid }"
              v-model.number="$v.productUser.productType.$model"
              required
            />
            <div v-if="$v.productUser.productType.$anyDirty && $v.productUser.productType.$invalid">
              <small class="form-text text-danger" v-if="!$v.productUser.productType.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.productUser.productType.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
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
            :disabled="$v.productUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-user-update.component.ts"></script>
