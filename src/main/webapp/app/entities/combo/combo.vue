<template>
  <div>
    <h2 id="page-heading" data-cy="ComboHeading">
      <span v-text="$t('dentalApp.combo.home.title')" id="combo-heading">Combos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.combo.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ComboCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-combo"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.combo.home.createLabel')"> Create a new Combo </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && combos && combos.length === 0">
      <span v-text="$t('dentalApp.combo.home.notFound')">No combos found</span>
    </div>
    <div class="table-responsive" v-if="combos && combos.length > 0">
      <table class="table table-striped" aria-describedby="combos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.name')">Name</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.price')">Price</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.discount')">Discount</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.weight')">Weight</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.point')">Point</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.description')">Description</span></th>
            <th scope="row"><span v-text="$t('dentalApp.combo.liked')">Liked</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="combo in combos" :key="combo.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ComboView', params: { comboId: combo.id } }">{{ combo.id }}</router-link>
            </td>
            <td>{{ combo.name }}</td>
            <td>{{ combo.price }}</td>
            <td>{{ combo.discount }}</td>
            <td>{{ combo.weight }}</td>
            <td>{{ combo.point }}</td>
            <td>{{ combo.description }}</td>
            <td>{{ combo.liked }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ComboView', params: { comboId: combo.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ComboEdit', params: { comboId: combo.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(combo)"
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
        ><span id="dentalApp.combo.delete.question" data-cy="comboDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-combo-heading" v-text="$t('dentalApp.combo.delete.question', { id: removeId })">
          Are you sure you want to delete this Combo?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-combo"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCombo()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./combo.component.ts"></script>
