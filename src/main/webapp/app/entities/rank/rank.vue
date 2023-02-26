<template>
  <div>
    <h2 id="page-heading" data-cy="RankHeading">
      <span v-text="$t('dentalApp.rank.home.title')" id="rank-heading">Ranks</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('dentalApp.rank.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'RankCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-rank">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('dentalApp.rank.home.createLabel')"> Create a new Rank </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && ranks && ranks.length === 0">
      <span v-text="$t('dentalApp.rank.home.notFound')">No ranks found</span>
    </div>
    <div class="table-responsive" v-if="ranks && ranks.length > 0">
      <table class="table table-striped" aria-describedby="ranks">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('dentalApp.rank.name')">Name</span></th>
            <th scope="row"><span v-text="$t('dentalApp.rank.turnoverCondition')">Turnover Condition</span></th>
            <th scope="row"><span v-text="$t('dentalApp.rank.discount')">Discount</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="rank in ranks" :key="rank.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RankView', params: { rankId: rank.id } }">{{ rank.id }}</router-link>
            </td>
            <td>{{ rank.name }}</td>
            <td>{{ rank.turnoverCondition }}</td>
            <td>{{ rank.discount }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RankView', params: { rankId: rank.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RankEdit', params: { rankId: rank.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(rank)"
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
        ><span id="dentalApp.rank.delete.question" data-cy="rankDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-rank-heading" v-text="$t('dentalApp.rank.delete.question', { id: removeId })">
          Are you sure you want to delete this Rank?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-rank"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeRank()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./rank.component.ts"></script>
