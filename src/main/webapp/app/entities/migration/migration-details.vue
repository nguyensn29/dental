<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="migration">
        <h2 class="jh-entity-heading" data-cy="migrationDetailsHeading">
          <span v-text="$t('dentalApp.migration.detail.title')">Migration</span> {{ migration.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.migration.migration')">Migration</span>
          </dt>
          <dd>
            <span>{{ migration.migration }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.migration.batch')">Batch</span>
          </dt>
          <dd>
            <span>{{ migration.batch }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="migration.id"
          :to="{ name: 'MigrationEdit', params: { migrationId: migration.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./migration-details.component.ts"></script>
