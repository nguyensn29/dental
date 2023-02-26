<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.combo.home.createOrEditLabel"
          data-cy="ComboCreateUpdateHeading"
          v-text="$t('dentalApp.combo.home.createOrEditLabel')"
        >
          Create or edit a Combo
        </h2>
        <div>
          <div class="form-group" v-if="combo.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="combo.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.name')" for="combo-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="combo-name"
              data-cy="name"
              :class="{ valid: !$v.combo.name.$invalid, invalid: $v.combo.name.$invalid }"
              v-model="$v.combo.name.$model"
              required
            />
            <div v-if="$v.combo.name.$anyDirty && $v.combo.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.combo.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.price')" for="combo-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="combo-price"
              data-cy="price"
              :class="{ valid: !$v.combo.price.$invalid, invalid: $v.combo.price.$invalid }"
              v-model.number="$v.combo.price.$model"
              required
            />
            <div v-if="$v.combo.price.$anyDirty && $v.combo.price.$invalid">
              <small class="form-text text-danger" v-if="!$v.combo.price.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.combo.price.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.discount')" for="combo-discount">Discount</label>
            <input
              type="number"
              class="form-control"
              name="discount"
              id="combo-discount"
              data-cy="discount"
              :class="{ valid: !$v.combo.discount.$invalid, invalid: $v.combo.discount.$invalid }"
              v-model.number="$v.combo.discount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.weight')" for="combo-weight">Weight</label>
            <input
              type="number"
              class="form-control"
              name="weight"
              id="combo-weight"
              data-cy="weight"
              :class="{ valid: !$v.combo.weight.$invalid, invalid: $v.combo.weight.$invalid }"
              v-model.number="$v.combo.weight.$model"
              required
            />
            <div v-if="$v.combo.weight.$anyDirty && $v.combo.weight.$invalid">
              <small class="form-text text-danger" v-if="!$v.combo.weight.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.combo.weight.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.point')" for="combo-point">Point</label>
            <input
              type="number"
              class="form-control"
              name="point"
              id="combo-point"
              data-cy="point"
              :class="{ valid: !$v.combo.point.$invalid, invalid: $v.combo.point.$invalid }"
              v-model.number="$v.combo.point.$model"
              required
            />
            <div v-if="$v.combo.point.$anyDirty && $v.combo.point.$invalid">
              <small class="form-text text-danger" v-if="!$v.combo.point.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.combo.point.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.description')" for="combo-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="combo-description"
              data-cy="description"
              :class="{ valid: !$v.combo.description.$invalid, invalid: $v.combo.description.$invalid }"
              v-model="$v.combo.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.combo.liked')" for="combo-liked">Liked</label>
            <input
              type="number"
              class="form-control"
              name="liked"
              id="combo-liked"
              data-cy="liked"
              :class="{ valid: !$v.combo.liked.$invalid, invalid: $v.combo.liked.$invalid }"
              v-model.number="$v.combo.liked.$model"
              required
            />
            <div v-if="$v.combo.liked.$anyDirty && $v.combo.liked.$invalid">
              <small class="form-text text-danger" v-if="!$v.combo.liked.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.combo.liked.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.combo.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./combo-update.component.ts"></script>
