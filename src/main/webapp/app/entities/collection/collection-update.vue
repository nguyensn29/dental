<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.collection.home.createOrEditLabel"
          data-cy="CollectionCreateUpdateHeading"
          v-text="$t('dentalApp.collection.home.createOrEditLabel')"
        >
          Create or edit a Collection
        </h2>
        <div>
          <div class="form-group" v-if="collection.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="collection.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collection.shopifyId')" for="collection-shopifyId">Shopify Id</label>
            <input
              type="number"
              class="form-control"
              name="shopifyId"
              id="collection-shopifyId"
              data-cy="shopifyId"
              :class="{ valid: !$v.collection.shopifyId.$invalid, invalid: $v.collection.shopifyId.$invalid }"
              v-model.number="$v.collection.shopifyId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collection.title')" for="collection-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="collection-title"
              data-cy="title"
              :class="{ valid: !$v.collection.title.$invalid, invalid: $v.collection.title.$invalid }"
              v-model="$v.collection.title.$model"
              required
            />
            <div v-if="$v.collection.title.$anyDirty && $v.collection.title.$invalid">
              <small class="form-text text-danger" v-if="!$v.collection.title.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collection.description')" for="collection-description"
              >Description</label
            >
            <input
              type="text"
              class="form-control"
              name="description"
              id="collection-description"
              data-cy="description"
              :class="{ valid: !$v.collection.description.$invalid, invalid: $v.collection.description.$invalid }"
              v-model="$v.collection.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.collection.handle')" for="collection-handle">Handle</label>
            <input
              type="text"
              class="form-control"
              name="handle"
              id="collection-handle"
              data-cy="handle"
              :class="{ valid: !$v.collection.handle.$invalid, invalid: $v.collection.handle.$invalid }"
              v-model="$v.collection.handle.$model"
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
            :disabled="$v.collection.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./collection-update.component.ts"></script>
