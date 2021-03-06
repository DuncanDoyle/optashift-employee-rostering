/*
 * Copyright (C) 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.openshift.employeerostering.gwtui.client.rostergrid.model;

import java.util.ArrayList;
import java.util.List;

public class SubLane<T> {

    private final List<Blob<T>> blobs;

    private final CollisionDetector<Blob<T>> collisionDetector;

    public SubLane() {
        this(new ArrayList<>());
    }

    public SubLane(final List<Blob<T>> blobs) {
        this.blobs = blobs;
        this.collisionDetector = new CollisionDetector<>(this::getBlobs);
    }

    public List<Blob<T>> getBlobs() {
        return blobs;
    }

    public CollisionDetector<Blob<T>> getCollisionDetector() {
        return collisionDetector;
    }

    public boolean anyCollide(final List<Blob<T>> blobs) {
        return blobs.stream().anyMatch(collisionDetector::collides);
    }

    public boolean collidesWith(final SubLane<T> other) {
        return anyCollide(other.blobs);
    }

    public SubLane<T> withMore(final List<Blob<T>> blobs) {
        final List<Blob<T>> newBlobs = new ArrayList<>(this.blobs);
        newBlobs.addAll(blobs);
        return new SubLane<>(newBlobs);
    }
}
