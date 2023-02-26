<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.migration.home.createOrEditLabel"
          data-cy="MigrationCreateUpdateHeading"
          v-text="$t('dentalApp.migration.home.createOrEditLabel')"
        >
          Create or edit a Migration
        </h2>
        <div>
          <div class="form-group" v-if="migration.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="migration.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.migration.migration')" for="migration-migration">Migration</label>
            <input
              type="text"
              class="form-control"
              name="migration"
              id="migration-migration"
              data-cy="migration"
              :class="{ valid: !$v.migration.migration.$invalid, invalid: $v.migration.migration.$invalid }"
              v-model="$v.migration.migration.$model"
              required
            />
            <div v-if="$v.migration.migration.$anyDirty && $v.migration.migration.$invalid">
              <small class="form-text text-danger" v-if="!$v.migration.migration.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.migration.batch')" for="migration-batch">Batch</label>
            <input
              type="number"
              class="form-control"
              name="batch"
              id="migration-batch"
              data-cy="batch"
              :class="{ valid: !$v.migration.batch.$invalid, invalid: $v.migration.batch.$invalid }"
              v-model.number="$v.migration.batch.$model"
              required
            />
            <div v-if="$v.migration.batch.$anyDirty && $v.migration.batch.$invalid">
              <small class="form-text text-danger" v-if="!$v.migration.batch.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.migration.batch.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.migration.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./migration-update.component.ts"></script>
