<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="dentalApp.message.home.createOrEditLabel"
          data-cy="MessageCreateUpdateHeading"
          v-text="$t('dentalApp.message.home.createOrEditLabel')"
        >
          Create or edit a Message
        </h2>
        <div>
          <div class="form-group" v-if="message.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="message.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.message.userId')" for="message-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="message-userId"
              data-cy="userId"
              :class="{ valid: !$v.message.userId.$invalid, invalid: $v.message.userId.$invalid }"
              v-model.number="$v.message.userId.$model"
              required
            />
            <div v-if="$v.message.userId.$anyDirty && $v.message.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.message.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.message.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.message.channel')" for="message-channel">Channel</label>
            <input
              type="text"
              class="form-control"
              name="channel"
              id="message-channel"
              data-cy="channel"
              :class="{ valid: !$v.message.channel.$invalid, invalid: $v.message.channel.$invalid }"
              v-model="$v.message.channel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.message.message')" for="message-message">Message</label>
            <input
              type="text"
              class="form-control"
              name="message"
              id="message-message"
              data-cy="message"
              :class="{ valid: !$v.message.message.$invalid, invalid: $v.message.message.$invalid }"
              v-model="$v.message.message.$model"
              required
            />
            <div v-if="$v.message.message.$anyDirty && $v.message.message.$invalid">
              <small class="form-text text-danger" v-if="!$v.message.message.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('dentalApp.message.isRead')" for="message-isRead">Is Read</label>
            <input
              type="number"
              class="form-control"
              name="isRead"
              id="message-isRead"
              data-cy="isRead"
              :class="{ valid: !$v.message.isRead.$invalid, invalid: $v.message.isRead.$invalid }"
              v-model.number="$v.message.isRead.$model"
              required
            />
            <div v-if="$v.message.isRead.$anyDirty && $v.message.isRead.$invalid">
              <small class="form-text text-danger" v-if="!$v.message.isRead.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.message.isRead.numeric" v-text="$t('entity.validation.number')">
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
            :disabled="$v.message.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./message-update.component.ts"></script>
