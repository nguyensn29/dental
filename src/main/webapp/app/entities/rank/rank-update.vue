<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.rank.home.createOrEditLabel"
          data-cy="RankCreateUpdateHeading"
          v-text="$t('dentalApp.rank.home.createOrEditLabel')"
        >
          Create or edit a Rank
        </h2>
        <div>
          <div class="form-group" v-if="rank.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="rank.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.rank.name')" for="rank-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="rank-name"
              data-cy="name"
              :class="{ valid: !$v.rank.name.$invalid, invalid: $v.rank.name.$invalid }"
              v-model="$v.rank.name.$model"
              required
            />
            <div v-if="$v.rank.name.$anyDirty && $v.rank.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.rank.name.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.rank.turnoverCondition')" for="rank-turnoverCondition"
              >Turnover Condition</label
            >
            <input
              type="number"
              class="form-control"
              name="turnoverCondition"
              id="rank-turnoverCondition"
              data-cy="turnoverCondition"
              :class="{ valid: !$v.rank.turnoverCondition.$invalid, invalid: $v.rank.turnoverCondition.$invalid }"
              v-model.number="$v.rank.turnoverCondition.$model"
              required
            />
            <div v-if="$v.rank.turnoverCondition.$anyDirty && $v.rank.turnoverCondition.$invalid">
              <small class="form-text text-danger" v-if="!$v.rank.turnoverCondition.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.rank.turnoverCondition.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.rank.discount')" for="rank-discount">Discount</label>
            <input
              type="number"
              class="form-control"
              name="discount"
              id="rank-discount"
              data-cy="discount"
              :class="{ valid: !$v.rank.discount.$invalid, invalid: $v.rank.discount.$invalid }"
              v-model.number="$v.rank.discount.$model"
              required
            />
            <div v-if="$v.rank.discount.$anyDirty && $v.rank.discount.$invalid">
              <small class="form-text text-danger" v-if="!$v.rank.discount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.rank.discount.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.rank.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./rank-update.component.ts"></script>
