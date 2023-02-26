<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="rank">
        <h2 class="jh-entity-heading" data-cy="rankDetailsHeading">
          <span v-text="$t('dentalApp.rank.detail.title')">Rank</span> {{ rank.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('dentalApp.rank.name')">Name</span>
          </dt>
          <dd>
            <span>{{ rank.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.rank.turnoverCondition')">Turnover Condition</span>
          </dt>
          <dd>
            <span>{{ rank.turnoverCondition }}</span>
          </dd>
          <dt>
            <span v-text="$t('dentalApp.rank.discount')">Discount</span>
          </dt>
          <dd>
            <span>{{ rank.discount }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="rank.id" :to="{ name: 'RankEdit', params: { rankId: rank.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./rank-details.component.ts"></script>
