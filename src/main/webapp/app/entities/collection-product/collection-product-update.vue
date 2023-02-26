<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.collectionProduct.home.createOrEditLabel"
          data-cy="CollectionProductCreateUpdateHeading"
          v-text="$t('dentalApp.collectionProduct.home.createOrEditLabel')"
        >
          Create or edit a CollectionProduct
        </h2>
        <div>
          <div class="form-group" v-if="collectionProduct.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="collectionProduct.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collectionProduct.collectionId')" for="collection-product-collectionId"
              >Collection Id</label
            >
            <input
              type="number"
              class="form-control"
              name="collectionId"
              id="collection-product-collectionId"
              data-cy="collectionId"
              :class="{ valid: !$v.collectionProduct.collectionId.$invalid, invalid: $v.collectionProduct.collectionId.$invalid }"
              v-model.number="$v.collectionProduct.collectionId.$model"
              required
            />
            <div v-if="$v.collectionProduct.collectionId.$anyDirty && $v.collectionProduct.collectionId.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.collectionProduct.collectionId.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.collectionProduct.collectionId.numeric"
                v-text="$t('entity.validation.number')"
              >
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collectionProduct.productId')" for="collection-product-productId"
              >Product Id</label
            >
            <input
              type="number"
              class="form-control"
              name="productId"
              id="collection-product-productId"
              data-cy="productId"
              :class="{ valid: !$v.collectionProduct.productId.$invalid, invalid: $v.collectionProduct.productId.$invalid }"
              v-model.number="$v.collectionProduct.productId.$model"
              required
            />
            <div v-if="$v.collectionProduct.productId.$anyDirty && $v.collectionProduct.productId.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.collectionProduct.productId.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.collectionProduct.productId.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.collectionProduct.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./collection-product-update.component.ts"></script>
