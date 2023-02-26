<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.cart.home.createOrEditLabel"
          data-cy="CartCreateUpdateHeading"
          v-text="$t('dentalApp.cart.home.createOrEditLabel')"
        >
          Create or edit a Cart
        </h2>
        <div>
          <div class="form-group" v-if="cart.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="cart.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.cart.userId')" for="cart-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="cart-userId"
              data-cy="userId"
              :class="{ valid: !$v.cart.userId.$invalid, invalid: $v.cart.userId.$invalid }"
              v-model.number="$v.cart.userId.$model"
              required
            />
            <div v-if="$v.cart.userId.$anyDirty && $v.cart.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.cart.productId')" for="cart-productId">Product Id</label>
            <input
              type="number"
              class="form-control"
              name="productId"
              id="cart-productId"
              data-cy="productId"
              :class="{ valid: !$v.cart.productId.$invalid, invalid: $v.cart.productId.$invalid }"
              v-model.number="$v.cart.productId.$model"
              required
            />
            <div v-if="$v.cart.productId.$anyDirty && $v.cart.productId.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.productId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.productId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.cart.variantId')" for="cart-variantId">Variant Id</label>
            <input
              type="number"
              class="form-control"
              name="variantId"
              id="cart-variantId"
              data-cy="variantId"
              :class="{ valid: !$v.cart.variantId.$invalid, invalid: $v.cart.variantId.$invalid }"
              v-model.number="$v.cart.variantId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.cart.productType')" for="cart-productType">Product Type</label>
            <input
              type="number"
              class="form-control"
              name="productType"
              id="cart-productType"
              data-cy="productType"
              :class="{ valid: !$v.cart.productType.$invalid, invalid: $v.cart.productType.$invalid }"
              v-model.number="$v.cart.productType.$model"
              required
            />
            <div v-if="$v.cart.productType.$anyDirty && $v.cart.productType.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.productType.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.productType.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.cart.qty')" for="cart-qty">Qty</label>
            <input
              type="number"
              class="form-control"
              name="qty"
              id="cart-qty"
              data-cy="qty"
              :class="{ valid: !$v.cart.qty.$invalid, invalid: $v.cart.qty.$invalid }"
              v-model.number="$v.cart.qty.$model"
              required
            />
            <div v-if="$v.cart.qty.$anyDirty && $v.cart.qty.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.qty.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.qty.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.cart.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./cart-update.component.ts"></script>
