<template>
  <div>
    <h2 id="page-heading" data-cy="SettingHeading">
      <span v-text="$t('dentalApp.setting.home.title')" id="setting-heading">Settings</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.setting.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'SettingCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-setting"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.setting.home.createLabel')"> Create a new Setting </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && settings && settings.length === 0">
      <span v-text="$t('dentalApp.setting.home.notFound')">No settings found</span>
    </div>
    <div class="table-responsive" v-if="settings && settings.length > 0">
      <table class="table table-striped" aria-describedby="settings">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.name')">Name</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.keyName')">Key Name</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.value')">Value</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.rule')">Rule</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.isNumber')">Is Number</span></th>
            <th scope="row"><span v-text="$t('dentalApp.setting.isObject')">Is Object</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="setting in settings" :key="setting.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SettingView', params: { settingId: setting.id } }">{{ setting.id }}</router-link>
            </td>
            <td>{{ setting.name }}</td>
            <td>{{ setting.keyName }}</td>
            <td>{{ setting.value }}</td>
            <td>{{ setting.rule }}</td>
            <td>{{ setting.isNumber }}</td>
            <td>{{ setting.isObject }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SettingView', params: { settingId: setting.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SettingEdit', params: { settingId: setting.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(setting)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="dentalApp.setting.delete.question" data-cy="settingDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-setting-heading" v-text="$t('dentalApp.setting.delete.question', { id: removeId })">
          Are you sure you want to delete this Setting?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-setting"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSetting()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./setting.component.ts"></script>
